package frameworks

import org.grooscript.jquery.GQuery
import spock.lang.Specification

/**
 * Created by jorge on 02/08/14.
 */
class FrameworksPresenterSpec extends Specification {

    def 'start presenter'() {
        when:
        presenter.start()

        then:
        1 * gQuery.bindAll(presenter)
        presenter.view
    }

    def 'add invalid new framework'() {
        given:
        presenter.nameFramework = 'Name'
        presenter.urlFramework = 'badUrl'
        presenter.view = view

        when:
        presenter.buttonAddFrameworkClick()

        then:
        1 * view.putError('Wrong url framework')
    }

    def 'add valid framework'() {
        given:
        presenter.nameFramework = 'Name'
        presenter.urlFramework = 'http://good.url'
        presenter.view = view

        when:
        presenter.buttonAddFrameworkClick()

        then:
        1 * gQuery.doRemoteCall('/addFramework', 'POST',
                [name: presenter.nameFramework, url: presenter.urlFramework, urlImage: null],
                _, _, Framework)
    }

    def 'html of initial frameworks'() {
        expect:
        FrameworksView.htmlFrameworks(FrameworksDb.listFrameworks) == "<ul id='listFrameworks'>" +
                "<li><div class='logo' data-anijs='if: mouseenter, do: flip animated'><img src='http://www.groovy.mn/static/yRPl4fzqI6siNjj2L8OfwDYV6F3S5nx81YszgJkOLv0.png'></img></div><a href='http://grails.org'>Grails</a></li>" +
                "<li><div class='logo' data-anijs='if: mouseenter, do: flip animated'><img src='http://2.bp.blogspot.com/-7gtrqKppoSc/VMz0XOw34kI/AAAAAAAAHgA/KG-nGwcbgQ0/s1600/gradle.png'></img></div><a href='http://gradle.org'>Gradle</a></li>" +
                "</ul>"
    }

    private view = Mock(View)
    private gQuery = Mock(GQuery)
    private presenter = new FrameworksPresenter(gQuery: gQuery)
}
