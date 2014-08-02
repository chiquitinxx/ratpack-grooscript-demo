package frameworks

/**
 * Created by jorge on 01/08/14.
 */
class FrameworksDb {

    private static final INITIAL_FRAMEWORKS = [
            new Framework(name: 'Grails', url: 'http://grails.org', urlImage: 'http://sqatutorial.com/wp-content/uploads/2014/01/photo.png'),
            new Framework(name: 'Gradle', url: 'http://gradle.org', urlImage: 'http://grails.org.mx/wp-content/uploads/2013/03/gradle-icon-512x512.png'),
            new Framework(name: 'Griffon', url: 'http://griffon.codehaus.org', urlImage: 'https://asset-2.kenai.com/attachments/images/project/griffon-1.png'),
    ]
    private static List<Framework> frameworks

    static List<Framework> getListFrameworks() {
        frameworks = frameworks ?: INITIAL_FRAMEWORKS
        frameworks
    }

    static boolean addNewFramework(Framework framework) {
        if (framework && framework.name
                && !listFrameworks.find { it.name.toUpperCase() == framework.name.toUpperCase()}) {
            frameworks << framework
            return true
        }
        false
    }
}
