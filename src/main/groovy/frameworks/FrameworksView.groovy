package frameworks

import org.grooscript.asts.GsNative
import org.grooscript.builder.HtmlBuilder

class FrameworksView implements View {

    String frameworksListSelector = '#listFrameworks'
    String errorSelector = '#validationError'

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

    void addNewFramework(Framework framework) {
        append frameworksListSelector, htmlFramework(framework)
    }

    void putError(String errorMessage) {
        putHtml errorSelector, errorMessage
    }

    @GsNative
    void putHtml(String selector, String html) {/*
        $(selector).html(html);
    */}

    @GsNative
    void append(String selector, String html) {/*
        $(selector).append(html);
        AniJS.run();
    */}
}
