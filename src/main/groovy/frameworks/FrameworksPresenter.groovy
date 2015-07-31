package frameworks

import org.grooscript.asts.GsNative
import org.grooscript.builder.HtmlBuilder
import org.grooscript.jquery.GQuery
import org.grooscript.jquery.GQueryImpl

/**
 * Created by jorge on 01/08/14.
 */
class FrameworksPresenter {

    String nameFramework
    String urlFramework
    String urlImageFramework
    GQuery gQuery

    void onLoad() {
        gQuery = new GQueryImpl()
        gQuery.bindAll(this)
    }

    static String htmlFrameworks(List<Framework> frameworks) {
        HtmlBuilder.build {
            ul(id: 'listFrameworks') {
                frameworks.each { framework ->
                    yieldUnescaped htmlFramework(framework)
                }
            }
        }
    }

    static String htmlFramework(Framework framework) {
        HtmlBuilder.build {
            li {
                div(class: 'logo', 'data-anijs': 'if: mouseenter, do: flip animated') {
                    if (!framework.hasImage() && framework.githubUrl()) {
                        img src: 'images/github.png'
                    } else {
                        img src: framework.hasImage() ? framework.urlImage : 'images/nologo.png'
                    }
                }
                a (href: framework.url, framework.name)
            }
        }
    }

    void buttonAddFrameworkClick() {

        def framework = new Framework(name: nameFramework, url: urlFramework, urlImage: urlImageFramework)
        def errors = framework.validate()
        if (!errors) {
            addFramework(framework, { newFramework ->
                append '#listFrameworks', htmlFramework(newFramework)
            }, { error ->
                putHtml('#validationError', 'Error from server!')
            })
        }
        putHtml('#validationError', errors.join(' - '))
    }

    @GsNative
    void putHtml(String selector, String html) {/*
        $(selector).html(html);
        AniJS.run();
    */}

    @GsNative
    void append(String selector, String html) {/*
        $(selector).append(html);
        AniJS.run();
    */}

    void addFramework(Framework framework, Closure onAdded, Closure onError = null) {
        gQuery.doRemoteCall('/addFramework',
                'POST', [name: framework.name, url: framework.url, urlImage: framework.urlImage],
                onAdded, onError, Framework)
    }
}
