package frameworks

class Framework {

    String name
    String url
    String urlImage

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

    boolean hasImage() {
        urlImage && ['.GIF', '.PNG', '.JPG'].any { urlImage.toUpperCase().endsWith(it)}
    }

    boolean githubUrl()  {
        url.contains('github.com')
    }

    private boolean validUrl(url) {
        ['http://', 'https://'].any { url && url.startsWith(it) }
    }

    private Closure hasEvilChars = { data ->
        data ? data.indexOf('<') >= 0 : false
    }

    boolean equals(Object other) {
        other instanceof Framework && other.name == this.name
    }
}
