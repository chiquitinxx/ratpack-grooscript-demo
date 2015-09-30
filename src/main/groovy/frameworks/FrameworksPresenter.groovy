package frameworks

import org.grooscript.jquery.GQuery

/**
 * Created by jorge on 01/08/14.
 */
class FrameworksPresenter {

    String nameFramework
    String urlFramework
    String urlImageFramework
    GQuery gQuery
    View view

    void start() {
        gQuery.bindAll(this)
        if (!view) view = new FrameworksView()
    }

    void buttonAddFrameworkClick() {

        def framework = new Framework(name: nameFramework, url: urlFramework, urlImage: urlImageFramework)
        def errors = framework.validate()
        if (!errors) {
            addFramework(framework, {newFramework ->
                view.addNewFramework newFramework
            }, {error ->
                view.putError('Error from server: ' + error)
            })
        }
        view.putError errors.join(' - ')
    }

    void addFramework(Framework framework, Closure onAdded, Closure onError = null) {
        gQuery.doRemoteCall('/addFramework',
                'POST', [name: framework.name, url: framework.url, urlImage: framework.urlImage],
                onAdded, onError, Framework)
    }
}
