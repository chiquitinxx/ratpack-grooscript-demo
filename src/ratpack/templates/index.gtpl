yieldUnescaped '<!DOCTYPE html>'
html {
  head {
    meta(charset:'utf-8')
    title("Ratpack grooscript demo")

    meta(name: 'apple-mobile-web-app-title', content: 'Ratpack grooscript demo')
    meta(name: 'description', content: '')
    meta(name: 'viewport', content: 'width=device-width, initial-scale=1')

    link(href: 'styles/style.css', type: "text/css", rel: 'stylesheet')
    link(href: 'styles/animate.css', type: "text/css", rel: 'stylesheet')
    script(src:"lib/jquery-1.11.1.min.js") {}
  }
  body {
    section {
      	div(id: "newFramework") {
		  p {
			yield 'Name:'
			input(type: 'text', id: 'nameFramework')
			yield '*'
		  }
		  p {
			yield 'Url framework:'
			input(type: 'text', id: 'urlFramework')
			yield '*'
		  }
		  p {
			yield 'Url image framework:'
			input(type: 'text', id: 'urlImageFramework')
		  }
		  input(type:"button", class:"doButton", id:"buttonAddFramework", value:"Add framework",
				 'data-anijs': "if: mousedown, do: shake animated, to: #validationError") {}
		  div(id: "validationError") {}
		  div(id: "mainTitle", 'data-anijs':"if: mouseenter, do: tada animated") {
			yield 'Groovy Frameworks'
		  }
      	}
    }

	section {
    	div model.htmlFrameworks
	}

	script(src:"lib/anijs-min.js") {}
	script(src:"lib/grooscript.min.js") {}
	script(src:"lib/grooscript-tools.js") {}
	script(src:"scripts/FrameworksPresenter.js") {}
    script {
    	yield '''
			jQuery(document).ready(function() {
                FrameworksPresenter({gQuery: GQueryImpl()}).start();
            });
    	'''
    }
  }
}