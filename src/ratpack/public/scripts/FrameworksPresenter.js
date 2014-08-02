function FrameworksPresenter() {
  var gSobject = gs.inherit(gs.baseClass,'FrameworksPresenter');
  gSobject.clazz = { name: 'frameworks.FrameworksPresenter', simpleName: 'FrameworksPresenter'};
  gSobject.clazz.superclass = { name: 'java.lang.Object', simpleName: 'Object'};
  gSobject.nameFramework = null;
  gSobject.urlFramework = null;
  gSobject.urlImageFramework = null;
  gSobject.gQuery = null;
  gSobject['onLoad'] = function(it) {
    var binder = Binder();
    gSobject.gQuery = gs.gp(binder,"gQuery");
    return (binder.delegate!=undefined?gs.applyDelegate(binder,binder.delegate,[this]):gs.executeCall(binder, [this]));
  }
  gSobject.htmlFrameworks = function(x0) { return FrameworksPresenter.htmlFrameworks(x0); }
  gSobject.htmlFramework = function(x0) { return FrameworksPresenter.htmlFramework(x0); }
  gSobject['buttonAddFrameworkClick'] = function(it) {
    var framework = Framework(gs.map().add("name",gSobject.nameFramework).add("url",gSobject.urlFramework).add("urlImage",gSobject.urlImageFramework));
    var errors = gs.mc(framework,"validate",[]);
    if (!gs.bool(errors)) {
      gs.mc(gSobject,"addFramework",[framework, function(newFramework) {
        return gs.mc(gSobject,"append",["#listFrameworks", gs.mc(this,"htmlFramework",[newFramework], gSobject)]);
      }, function(error) {
        return gs.mc(gSobject,"putHtml",["#validationError", "Error from server!"]);
      }]);
    };
    return gs.mc(gSobject,"putHtml",["#validationError", gs.mc(errors,"join",[" - "])]);
  }
  gSobject.putHtml = function(selector, html) {
    $(selector).html(html);
        AniJS.run();
  }
  gSobject.append = function(selector, html) {
    $(selector).append(html);
        AniJS.run();
  }
  gSobject['addFramework'] = function(framework, onAdded, onError) {
    if (onError === undefined) onError = null;
    return gs.mc(gSobject.gQuery,"doRemoteCall",["/addFramework", "POST", gs.map().add("name",gs.gp(framework,"name")).add("url",gs.gp(framework,"url")).add("urlImage",gs.gp(framework,"urlImage")), onAdded, onError, Framework]);
  }
  if (arguments.length == 1) {gs.passMapToObject(arguments[0],gSobject);};
  
  return gSobject;
};
FrameworksPresenter.htmlFrameworks = function(frameworks) {
  return gs.mc(HtmlBuilder,"build",[function(it) {
    return gs.mc(FrameworksPresenter,"ul",[gs.map().add("id","listFrameworks"), function(it) {
      return gs.mc(frameworks,"each",[function(framework) {
        return gs.mc(FrameworksPresenter,"yield",[gs.mc(FrameworksPresenter,"htmlFramework",[framework])]);
      }]);
    }]);
  }]);
}
FrameworksPresenter.htmlFramework = function(framework) {
  return gs.mc(HtmlBuilder,"build",[function(it) {
    return gs.mc(FrameworksPresenter,"li",[function(it) {
      gs.mc(FrameworksPresenter,"div",[gs.map().add("class","logo").add("data-anijs","if: mouseenter, do: flip animated"), function(it) {
        if ((!gs.mc(framework,"hasImage",[])) && (gs.mc(framework,"githubUrl",[]))) {
          return gs.mc(FrameworksPresenter,"img",[gs.map().add("src","images/github.png")]);
        } else {
          return gs.mc(FrameworksPresenter,"img",[gs.map().add("src",(gs.mc(framework,"hasImage",[]) ? gs.gp(framework,"urlImage") : "images/nologo.png"))]);
        };
      }]);
      return gs.mc(FrameworksPresenter,"a",[gs.map().add("href",gs.gp(framework,"url")), gs.gp(framework,"name")]);
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
  gSobject['hasImage'] = function(it) {
    return (gs.bool(gSobject.urlImage)) && (gs.mc(gs.list([".GIF" , ".PNG" , ".JPG"]),"any",[function(it) {
      return gs.mc(gs.mc(gSobject.urlImage,"toUpperCase",[]),"endsWith",[it]);
    }]));
  }
  gSobject['equals'] = function(other) {
    return (gs.instanceOf(other, "Framework")) && (gs.equals(gs.gp(other,"name"), gs.gp(gs.thisOrObject(this,gSobject),"name")));
  }
  gSobject['githubUrl'] = function(it) {
    return gs.mc(gSobject.url,"contains",["github.com"]);
  }
  gSobject['validate'] = function(it) {
    var validationErrors = gs.list([]);
    if (!gs.bool(gSobject.name)) {
      gs.mc(validationErrors,'leftShift', gs.list(["Missing name framework"]));
    };
    if (!gs.bool(gSobject.url)) {
      gs.mc(validationErrors,'leftShift', gs.list(["Missing url framework"]));
    };
    if ((gs.bool(gSobject.url)) && (!gs.mc(gSobject,"validUrl",[gSobject.url]))) {
      gs.mc(validationErrors,'leftShift', gs.list(["Wrong url framework"]));
    };
    if ((gs.bool(gSobject.urlImage)) && (!gs.mc(gSobject,"validUrl",[gSobject.urlImage]))) {
      gs.mc(validationErrors,'leftShift', gs.list(["Wrong url image"]));
    };
    if (gs.mc(gs.list([gSobject.name , gSobject.url , gSobject.urlImage]),"any",[gSobject.hasEvilChars])) {
      gs.mc(validationErrors,'leftShift', gs.list(["Wrong chars"]));
    };
    return validationErrors;
  }
  gSobject['validUrl'] = function(url) {
    return gs.mc(gs.list(["http://" , "https://"]),"any",[function(it) {
      return (gs.bool(gSobject.url)) && (gs.mc(gSobject.url,"startsWith",[it]));
    }]);
  }
  if (arguments.length == 1) {gs.passMapToObject(arguments[0],gSobject);};
  
  return gSobject;
};
