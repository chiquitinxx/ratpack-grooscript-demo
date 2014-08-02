package frameworks

/**
 * Created by jorge on 01/03/14.
 */
class Framework {

    String name
    String url
    String urlImage

    boolean hasImage() {
        urlImage && ['.GIF', '.PNG', '.JPG'].any { urlImage.toUpperCase().endsWith(it)}
    }

    boolean equals(Object other) {
        other instanceof Framework && other.name == this.name
    }

    boolean githubUrl()  {
        url.contains('github.com')
    }

    List<String> validate() {
        def validationErrors = []

        if (!name) {
            validationErrors << 'Missing name framework'
        }
        if (!url) {
            validationErrors << 'Missing url framework'
        }
        if (url && !validUrl(url)) {
            validationErrors << 'Wrong url framework'
        }
        if (urlImage && !validUrl(urlImage)) {
            validationErrors << 'Wrong url image'
        }
        if ([name, url, urlImage].any(hasEvilChars)) {
            validationErrors << 'Wrong chars'
        }
        validationErrors
    }

    private boolean validUrl(url) {
        ['http://', 'https://'].any { url && url.startsWith(it) }
    }

    private Closure hasEvilChars = { data ->
        data ? data.indexOf('<') >= 0 : false
    }
}
