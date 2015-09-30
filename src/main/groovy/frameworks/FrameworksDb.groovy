package frameworks

/**
 * Created by jorge on 01/08/14.
 */
class FrameworksDb {

    private static final INITIAL_FRAMEWORKS = [
            new Framework(name: 'Grails', url: 'http://grails.org', urlImage: 'http://www.groovy.mn/static/yRPl4fzqI6siNjj2L8OfwDYV6F3S5nx81YszgJkOLv0.png'),
            new Framework(name: 'Gradle', url: 'http://gradle.org', urlImage: 'http://2.bp.blogspot.com/-7gtrqKppoSc/VMz0XOw34kI/AAAAAAAAHgA/KG-nGwcbgQ0/s1600/gradle.png'),
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
