import frameworks.*
import ratpack.form.Form
import ratpack.groovy.template.MarkupTemplateModule

import static ratpack.groovy.Groovy.groovyMarkupTemplate
import static ratpack.groovy.Groovy.ratpack
import static ratpack.jackson.Jackson.json

ratpack {
    bindings {
        module MarkupTemplateModule
    }

    handlers {
        get {
            def htmlFrameworks = FrameworksView.htmlFrameworks(FrameworksDb.listFrameworks)
            render groovyMarkupTemplate([htmlFrameworks: htmlFrameworks], "index.gtpl")
        }

        post('addFramework') {
            parse(Form).then { readForm ->
                Framework framework = new Framework(readForm)
                def valErrors = framework.validate()
                if (!valErrors && FrameworksDb.addNewFramework(framework)) {
                    render json(framework)
                } else {
                    render null
                }
            }
        }

        files { dir "public" }
    }
}
