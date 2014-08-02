package frameworks

import spock.lang.Specification

/**
 * Created by jorge on 02/08/14.
 */
class FrameworksPresenterSpec extends Specification {

    def 'html of initial frameworks'() {
        expect:
        FrameworksPresenter.htmlFrameworks(FrameworksDb.listFrameworks) == "<ul id='listFrameworks'>" +
                "<li><div class='logo' data-anijs='if: mouseenter, do: flip animated'><img src='http://sqatutorial.com/wp-content/uploads/2014/01/photo.png'></img></div><a href='http://grails.org'>Grails</a></li>" +
                "<li><div class='logo' data-anijs='if: mouseenter, do: flip animated'><img src='http://grails.org.mx/wp-content/uploads/2013/03/gradle-icon-512x512.png'></img></div><a href='http://gradle.org'>Gradle</a></li>" +
                "<li><div class='logo' data-anijs='if: mouseenter, do: flip animated'><img src='https://asset-2.kenai.com/attachments/images/project/griffon-1.png'></img></div><a href='http://griffon.codehaus.org'>Griffon</a></li>" +
                "</ul>"
    }
}
