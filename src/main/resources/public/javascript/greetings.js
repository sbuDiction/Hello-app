
let nameInput = document.querySelector('.name');
let language = document.querySelector('.language');
let greetButton = document.querySelector('.greet');


let dropdown = document.querySelector('.dropdownLanguage').innerHTML;
let dropdownCompiler = Handlebars.compile(dropdown);
let dropdownData = document.querySelector('.languageDrop');

const pageContent = document.querySelector('.greetingsMain');

const greetingsMessage = document.querySelector('.greetingMessage');
const greetMsgCompiler = Handlebars.compile(greetingsMessage.innerHTML);

const greetedNames = document.querySelector('.greetedNames');
const greetedNamesCompiler = Handlebars.compile(greetedNames.innerHTML);


const get_names = () => {
    axios.get('/api/greet/greeted/names')
        .then((response) => {
            let userName = response.data
            console.log(response.data);

            let showHtml = greetedNamesCompiler({ names: userName });
            pageContent.innerHTML = showHtml;

        })
}

const get_language = () => {
    axios.get('/api/greetings/language')
        .then((response) => {
            let languageList = response.data
            console.log(languageList);
            

            let displayHtml = dropdownCompiler({ lang: languageList })
            dropdownData.innerHTML = displayHtml;
        })
}

async function greet() {
    const self = this;
    let userName = nameInput.value;
    let languageSelected = language.value;

    let params = {
        "userName": userName,
        "language": languageSelected
    }
    console.log(params);
    

    await axios.post('/api/greetings/greet', params)
        .then((results) => {
            let data = results.data;
            self.language = data.language;
            console.log(results);
        })
}

const greetingMessage = () => {
    axios.get('/api/greetings/message')
        .then((response) => {
            let message = response.data;
            console.log(message);

            let displayHtml = greetMsgCompiler({ greetings: message });
            pageContent.innerHTML = displayHtml;
        })
}

greetButton.addEventListener('click', function () {
    greet()
})

window.onhashchange = () => {
    if (location.hash === '#greeted') {
        get_names()
    }

}

// const toggleLoader = () => {
//     const spinner = document.querySelector('.loader');
//     spinner.classList.toggle('hidden');
// }

let startTime = new Date().getTime();
const onLoadEventHandler = () => {
    let latency = new Date().getTime() - startTime;
    console.log(latency);

}
onLoadEventHandler();


$(document).ready(function () {
    $('.ui.dropdown').dropdown();
    setTimeout(function () {
        $('#dimm').hide();
    }, 1000)

    $(document).ready(function () {
        $('.ui.accordion').accordion();
    });
});

document.addEventListener('DOMContentLoaded', function () {
    get_language();
})
