let nameInput = document.querySelector('.name');
let language = document.querySelector('.language');
let greetButton = document.querySelector('.greet');


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
    axios.get('/api/greet/greeted/names')
        .then((response) => {
            let userName = response.data
            let showHtml = greetedNamesCompiler({ names: userName });
            pageContent.innerHTML = showHtml;
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
    get_counter();
    if (url[1] === 'home') {
        get_counter();
    } else if (url[1] === 'greeted') {
        get_names();
    }
}

let startTime = new Date().getTime();
const onLoadEventHandler = () => {
    let latency = new Date().getTime() - startTime;
    return latency;
}


$(document).ready(function () {
    $('.ui.dropdown').dropdown();
    setTimeout(function () {
        $('#dimm').hide();
    }, onLoadEventHandler)

    $(document).ready(function () {
        $('.ui.accordion').accordion();
    });
    get_language();
    buildHomePage();
});

greetButton.addEventListener('click', () => {
    greet()
})


