import frameworks.*
import ratpack.form.Form
import ratpack.groovy.template.MarkupTemplateModule

import static ratpack.groovy.Groovy.groovyMarkupTemplate
import static ratpack.groovy.Groovy.ratpack
import static ratpack.jackson.Jackson.json

ratpack {
    bindings {
        module MarkupTemplateModule //<1>
    }

    handlers {
        get {
            def htmlFrameworks = FrameworksView.htmlFrameworks(FrameworksDb.listFrameworks)//<2>
            render groovyMarkupTemplate([htmlFrameworks: htmlFrameworks], "index.gtpl")//<3>
        }

        post('addFramework') {
            parse(Form).then { readForm ->
                Framework framework = new Framework(readForm)
                def valErrors = framework.validate() //<4>
                if (!valErrors && FrameworksDb.addNewFramework(framework)) {
                    render json(framework) //<5>
                } else {
                    render null
                }
            }
        }

        files { dir "public" } //<6>
    }
}
