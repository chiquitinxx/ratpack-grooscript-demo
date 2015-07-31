import frameworks.Framework
import frameworks.FrameworksDb
import frameworks.FrameworksPresenter
import ratpack.form.Form
import ratpack.jackson.guice.JacksonModule
import ratpack.groovy.template.MarkupTemplateModule

import static ratpack.groovy.Groovy.groovyMarkupTemplate
import static ratpack.groovy.Groovy.ratpack
import static ratpack.jackson.Jackson.json

ratpack {
    bindings {
        module JacksonModule
        module MarkupTemplateModule
    }

    handlers {
        get {
            def list = FrameworksPresenter.htmlFrameworks(FrameworksDb.listFrameworks)
            render groovyMarkupTemplate([frameworks: list], "index.gtpl")
        }

        post('addFramework') {
            Framework framework = new Framework(parse(Form))
            def valErrors = framework.validate()
            if (!valErrors && FrameworksDb.addNewFramework(framework)) {
                render json(framework)
            } else {
                render null
            }
        }

        files { dir "public" }
    }
}
