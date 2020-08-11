
let nameInput = document.querySelector('.name');
let language = document.querySelector('.language');
let greetButton = document.querySelector('.greet');


let dropdown = document.querySelector('.dropdownLanguage').innerHTML;
let dropdownCompiler = Handlebars.compile(dropdown);
let dropdownData = document.querySelector('.languageDrop');

const pageContent = document.querySelector('.greetingsMain');



const greetedNames = document.querySelector('.greetedNames');
const greetedNamesCompiler = Handlebars.compile(greetedNames.innerHTML);

const home = document.querySelector('.greetingsHomePage');
const homeCompiler = Handlebars.compile(home.innerHTML);

// const greetingMessage = document.querySelector('.greetingMessage');
// const greetMsgCompiler = Handlebars.compile(greetingMessage.innerHTML);

const get_names = () => {
    axios.get('/api/greet/greeted/names')
        .then((response) => {
            let userName = response.data
            console.log(response.data);

            let showHtml = greetedNamesCompiler({ names: userName });
            pageContent.innerHTML = showHtml;

        })
}

const get_counter = () => {
    axios.get('/api/greetings/counter')
        .then((response) => {
            console.log(response.data);

            let displayHtml = homeCompiler({ counter: response.data });
            pageContent.innerHTML = displayHtml;
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

    // const greetMsgCompiler = Handlebars.compile(greetingsMessage.innerHTML);
    let userName = nameInput.value;
    let languageSelected = language.value;

    let params = {
        "userName": userName,
        "language": languageSelected
    }

    await axios.post('/api/greetings/greet', params)
        .then((results) => {
            // let displayCount = homeCompiler({ greetings: results.data });
            // pageContent.innerHTML = displayHtml;

            // const greetingMessage = document.querySelector('.greetingMessage');
            // const greetMsgCompiler = Handlebars.compile(greetingMessage.innerHTML);

            // console.log(greetingMessage);


            // let displayGreetings = homeCompiler({ greetings: results.data })
            // console.log(displayGreetings);

            // pageContent.innerHTML = displayGreetings;
            get_counter();
        })
}

// get_counter();

window.onload = () => {
    window.location.href = '/#/home/';
}

window.onhashchange = () => {
    const hash = location.hash;
    const url = hash.split('/');
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
});

document.addEventListener('DOMContentLoaded', () => {
    get_language();
})

greetButton.addEventListener('click', () => {
    // greet()
    console.log(greet());

})

const buildHomePage = () => {
    // const greetingsMessage = document.querySelector('.greetingMessage');
    // const greetMsgCompiler = Handlebars.compile(greetingsMessage.innerHTML);
    let html = homeCompiler();
    pageContent.innerHTML = html;
    get_counter();
}
buildHomePage();