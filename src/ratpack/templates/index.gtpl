yieldUnescaped '<!DOCTYPE html>'
html {
  head {
    meta(charset:'utf-8')
    title("Ratpack grooscript demo")

    link(href: 'styles/style.css', type: "text/css", rel: 'stylesheet')
    link(href: 'styles/animate.css', type: "text/css", rel: 'stylesheet')
  }
// tag::doc[]
  body {
    head {
      	div(id: "newFramework") {
		  p { //<1>
			yield 'Name:';input(type: 'text', id: 'nameFramework');yield '*'
		  }
		  p {
			yield 'Url framework:';input(type: 'text', id: 'urlFramework');yield '*'
		  }
		  p {
			yield 'Url image framework:';input(type: 'text', id: 'urlImageFramework')
		  }
		  input(type:"button", class:"doButton", id:"buttonAddFramework", value:"Add framework",
				 'data-anijs': "if: mousedown, do: shake animated, to: #validationError") {} //<2>
		  div(id: "validationError") {} //<3>
		  div(id: "mainTitle", 'data-anijs':"if: mouseenter, do: tada animated") {
			yield 'Groovy Frameworks'
		  }
      	}
    }

	section {
    	div model.htmlFrameworks //<4>
	}

	["lib/jquery-1.11.1.min.js", "lib/anijs-min.js", "lib/grooscript.min.js",
		"lib/grooscript-tools.js", "scripts/FrameworksPresenter.js"].each {
		script(src: it) {} //<5>
	}
	script {
    	yield '''
			jQuery(document).ready(function() {
                FrameworksPresenter({gQuery: GQueryImpl()}).start(); //<6>
            });
    	'''
    }
  }
// end::doc[]
}