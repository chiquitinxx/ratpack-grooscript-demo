package frameworks

import org.grooscript.asts.GsNative
import org.grooscript.builder.HtmlBuilder
import org.grooscript.jquery.Binder
import org.grooscript.jquery.GQuery

/**
 * Created by jorge on 01/08/14.
 */
class FrameworksPresenter {

    def nameFramework
    def urlFramework
    def urlImageFramework
    GQuery gQuery

    def onLoad() {
        def binder = new Binder()
        gQuery = binder.gQuery
        binder(this);
    }

    static String htmlFrameworks(List<Framework> frameworks) {
        HtmlBuilder.build {
            ul(id: 'listFrameworks') {
                frameworks.each { framework ->
                    yield htmlFramework(framework)
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

    def buttonAddFrameworkClick() {

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

    def addFramework(Framework framework, Closure onAdded, Closure onError = null) {
        gQuery.doRemoteCall('/addFramework',
                'POST', [name: framework.name, url: framework.url, urlImage: framework.urlImage],
                onAdded, onError, Framework)
    }
}
