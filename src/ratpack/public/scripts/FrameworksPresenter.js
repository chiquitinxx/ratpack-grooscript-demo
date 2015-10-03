function FrameworksView() {
  var gSobject = gs.inherit(gs.baseClass,'FrameworksView');
  gSobject.clazz = { name: 'frameworks.FrameworksView', simpleName: 'FrameworksView'};
  gSobject.clazz.superclass = { name: 'java.lang.Object', simpleName: 'Object'};
  gSobject.clazz.interfaces = [{ name: 'frameworks.View', simpleName: 'View'}];
  gSobject.frameworksListSelector = "#listFrameworks";
  gSobject.errorSelector = "#validationError";
  gSobject.htmlFrameworks = function(x0) { return FrameworksView.htmlFrameworks(x0); }
  gSobject.htmlFramework = function(x0) { return FrameworksView.htmlFramework(x0); }
  gSobject['addNewFramework'] = function(framework) {
    return gs.mc(gSobject,"append",[gSobject.frameworksListSelector, FrameworksView.htmlFramework(framework)]);
  }
  gSobject['putError'] = function(errorMessage) {
    return gs.mc(gSobject,"putHtml",[gSobject.errorSelector, errorMessage]);
  }
  gSobject.putHtml = function(selector, html) {
    $(selector).html(html);
  }
  gSobject.append = function(selector, html) {
    $(selector).append(html);
        AniJS.run();
  }
  if (arguments.length == 1) {gs.passMapToObject(arguments[0],gSobject);};
  
  return gSobject;
};
FrameworksView.htmlFrameworks = function(frameworks) {
  return gs.execStatic(HtmlBuilder,'build', this,[function(it) {
    return gs.mc(FrameworksView,"ul",[gs.map().add("id","listFrameworks"), function(it) {
      return gs.mc(frameworks,"each",[function(framework) {
        return gs.mc(FrameworksView,"yieldUnescaped",[gs.mc(FrameworksView,"htmlFramework",[framework])]);
      }]);
    }]);
  }]);
}
FrameworksView.htmlFramework = function(framework) {
  return gs.execStatic(HtmlBuilder,'build', this,[function(it) {
    return gs.mc(FrameworksView,"li",[function(it) {
      gs.mc(FrameworksView,"div",[gs.map().add("class","logo").add("data-anijs","if: mouseenter, do: flip animated"), function(it) {
        if ((!gs.bool(gs.mc(framework,"hasImage",[]))) && (gs.mc(framework,"githubUrl",[]))) {
          return gs.mc(FrameworksView,"img",[gs.map().add("src","images/github.png")]);
        } else {
          return gs.mc(FrameworksView,"img",[gs.map().add("src",(gs.mc(framework,"hasImage",[]) ? gs.gp(framework,"urlImage") : "images/nologo.png"))]);
        };
      }]);
      return gs.mc(FrameworksView,"a",[gs.map().add("href",gs.gp(framework,"url")), gs.gp(framework,"name")]);
    }]);
  }]);
}
function Framework() {
  var gSobject = gs.inherit(gs.baseClass,'Framework');
  gSobject.clazz = { name: 'frameworks.Framework', simpleName: 'Framework'};
  gSobject.clazz.superclass = { name: 'java.lang.Object', simpleName: 'Object'};
  gSobject.name = null;
  gSobject.url = null;
  gSobject.urlImage = null;
  gSobject.hasEvilChars = function(data) {
    return (gs.bool(data) ? gs.mc(data,"indexOf",["<"]) >= 0 : false);
  };
  gSobject['validate'] = function(it) {
    var validationErrors = gs.list([]);
    if (!gs.bool(gSobject.name)) {
      gs.mc(validationErrors,'leftShift', gs.list(["Missing name framework"]));
    };
    if (!gs.bool(gSobject.url)) {
      gs.mc(validationErrors,'leftShift', gs.list(["Missing url framework"]));
    };
    if ((gs.bool(gSobject.url)) && (!gs.bool(gs.mc(gSobject,"validUrl",[gSobject.url])))) {
      gs.mc(validationErrors,'leftShift', gs.list(["Wrong url framework"]));
    };
    if ((gs.bool(gSobject.urlImage)) && (!gs.bool(gs.mc(gSobject,"validUrl",[gSobject.urlImage])))) {
      gs.mc(validationErrors,'leftShift', gs.list(["Wrong url image"]));
    };
    if (gs.mc(gs.list([gSobject.name , gSobject.url , gSobject.urlImage]),"any",[gSobject.hasEvilChars])) {
      gs.mc(validationErrors,'leftShift', gs.list(["Wrong chars"]));
    };
    return validationErrors;
  }
  gSobject['hasImage'] = function(it) {
    return (gs.bool(gSobject.urlImage)) && (gs.mc(gs.list([".GIF" , ".PNG" , ".JPG"]),"any",[function(it) {
      return gs.mc(gs.mc(gSobject.urlImage,"toUpperCase",[]),"endsWith",[it]);
    }]));
  }
  gSobject['githubUrl'] = function(it) {
    return gs.mc(gSobject.url,"contains",["github.com"]);
  }
  gSobject['validUrl'] = function(url) {
    return gs.mc(gs.list(["http://" , "https://"]),"any",[function(it) {
      return (gs.bool(url)) && (gs.mc(url,"startsWith",[it]));
    }]);
  }
  gSobject['equals'] = function(other) {
    return (gs.instanceOf(other, "Framework")) && (gs.equals(gs.gp(other,"name"), gs.gp(gs.thisOrObject(this,gSobject),"name")));
  }
  if (arguments.length == 1) {gs.passMapToObject(arguments[0],gSobject);};
  
  return gSobject;
};
function FrameworksPresenter() {
  var gSobject = gs.inherit(gs.baseClass,'FrameworksPresenter');
  gSobject.clazz = { name: 'frameworks.FrameworksPresenter', simpleName: 'FrameworksPresenter'};
  gSobject.clazz.superclass = { name: 'java.lang.Object', simpleName: 'Object'};
  gSobject.nameFramework = null;
  gSobject.urlFramework = null;
  gSobject.urlImageFramework = null;
  gSobject.gQuery = null;
  gSobject.view = null;
  gSobject['start'] = function(it) {
    gs.mc(gSobject.gQuery,"bindAll",[this]);
    if (!gs.bool(gSobject.view)) {
      return gSobject.view = FrameworksView();
    };
  }
  gSobject['buttonAddFrameworkClick'] = function(it) {
    var framework = Framework(gs.map().add("name",gSobject.nameFramework).add("url",gSobject.urlFramework).add("urlImage",gSobject.urlImageFramework));
    var errors = gs.mc(framework,"validate",[]);
    if (!gs.bool(errors)) {
      gs.mc(gSobject,"addFramework",[framework, function(newFramework) {
        return gs.mc(gSobject.view,"addNewFramework",[newFramework]);
      }, function(error) {
        return gs.mc(gSobject.view,"putError",[gs.plus("Error from server: ", error)]);
      }]);
    };
    return gs.mc(gSobject.view,"putError",[gs.mc(errors,"join",[" - "])]);
  }
  gSobject['addFramework'] = function(framework, onAdded, onError) {
    if (onError === undefined) onError = null;
    return gs.mc(gSobject.gQuery,"doRemoteCall",["/addFramework", "POST", gs.map().add("name",gs.gp(framework,"name")).add("url",gs.gp(framework,"url")).add("urlImage",gs.gp(framework,"urlImage")), onAdded, onError, Framework]);
  }
  if (arguments.length == 1) {gs.passMapToObject(arguments[0],gSobject);};
  
  return gSobject;
};
