let nameInput = document.querySelector('.name');
let language = document.querySelector('.language');
let greetButton = document.querySelector('.greet');
const greetingBox = document.querySelector('.content');
const homeUrl = document.querySelector('.homeUrl');
const greetedUrl = document.querySelector('.greetedUrl');


let dropdown = document.querySelector('.dropdownLanguage').innerHTML;
let dropdownCompiler = Handlebars.compile(dropdown);
let dropdownData = document.querySelector('.languageDrop');

const pageContent = document.querySelector('.greetingsMain');
const displayPhrase = document.querySelector('.greetins');


const greetedNames = document.querySelector('.greetedNames');
const greetedNamesCompiler = Handlebars.compile(greetedNames.innerHTML);

const home = document.querySelector('.greetingsHomePage');
const homeCompiler = Handlebars.compile(home.innerHTML);

const greetingMessage = document.querySelector('.message');
const greetMsgCompiler = Handlebars.compile(greetingMessage.innerHTML);


const get_counter = () => {
    axios.get('/api/greetings/counter')
        .then((response) => {
            let displayHtml = homeCompiler({ counter: response.data });
            pageContent.innerHTML = displayHtml;
        })
}

const buildHomePage = () => {
    let html = homeCompiler();
    pageContent.innerHTML = html;
    get_counter();
}

const get_names = () => {
    axios.get('/api/greet/greeted/names', { api_call_time: new Date().getTime() })
        .then((response) => {

            let userName = response.data;
            let showHtml = greetedNamesCompiler({ names: userName });
            pageContent.innerHTML = showHtml;
            const loader = document.getElementById('tableLoader');

            if (response) {
                greetingBox.classList.add('hide')
                loader.classList.add('active');
            } if (response.status === 200) {
                setTimeout(() => {
                    loader.classList.remove('active');
                    greetingBox.classList.remove('hide');
                }, onLoadEventHandler(response.config.api_call_time))
            }
        })
}

const get_language = () => {
    axios.get('/api/greetings/language')
        .then((response) => {
            let languageList = response.data
            let displayHtml = dropdownCompiler({ lang: languageList })
            dropdownData.innerHTML = displayHtml;
        })
}

async function greet() {
    let userName = nameInput.value;
    let languageSelected = language.value;
    let params = {
        "userName": userName,
        "language": languageSelected,
        // "timeStamp": new Date()
    }

    await axios.post('/api/greetings/greet', params)
        .then((results) => {
            let displayGreetings = greetMsgCompiler({ greetings: results.data })
            pageContent.innerHTML = displayGreetings;
            setTimeout(() => {
                buildHomePage();
                get_counter();
            }, 2000)
        })
}

window.onload = () => {
    window.location.href = '/#/home/';
}

window.onhashchange = () => {
    let hash = location.hash;
    let url = hash.split('/');
    if (url[1] === 'greeted') {
        homeUrl.classList.remove('active');
        greetedUrl.classList.add('active');
        get_names();
    } else if (url[1] === 'home') {
        homeUrl.classList.add('active');
        greetedUrl.classList.remove('active');
        get_counter();
    }
}

let pageLoadingTime = new Date().getTime();
const onLoadEventHandler = (time) => {
    return new Date().getTime() - time;
}


$(document).ready(function () {
    $('.ui.dropdown').dropdown();
    setTimeout(function () {
        $('#dimm').hide();
    }, onLoadEventHandler(pageLoadingTime))

    $(document).ready(function () {
        $('.ui.accordion').accordion();
    });
    get_language();
    buildHomePage();
});

greetButton.addEventListener('click', () => {
    greet()
})


