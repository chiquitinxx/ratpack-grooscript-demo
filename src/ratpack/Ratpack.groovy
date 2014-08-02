import frameworks.Framework
import frameworks.FrameworksDb
import frameworks.FrameworksPresenter
import ratpack.form.Form

import static ratpack.groovy.Groovy.groovyTemplate
import static ratpack.groovy.Groovy.ratpack
import ratpack.jackson.JacksonModule

import static ratpack.jackson.Jackson.json

ratpack {
    bindings {
        add new JacksonModule()
    }

    handlers {
        get {
            render groovyTemplate("index.html",
                    frameworks: FrameworksPresenter.htmlFrameworks(FrameworksDb.listFrameworks))
        }

        post('addFramework') {
            def framework = new Framework(parse(Form))
            def valErrors = framework.validate()
            if (!valErrors && FrameworksDb.addNewFramework(framework)) {
                render json(framework)
            } else {
                render null
            }
        }
        
        assets "public"
    }
}
