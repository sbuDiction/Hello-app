
let nameInput = document.querySelector('.name');
let language = document.querySelector('.language');
let greetButton = document.querySelector('.greet');


let dropdown = document.querySelector('.dropdownLanguage').innerHTML;
let dropdownCompiler = Handlebars.compile(dropdown);
let dropdownData = document.querySelector('.languageDrop');


const get_names = () => {
    axios.get('/api/greet/greeted/names')
        .then((response) => {
            let results = response.data
            console.log(results);
        })
}

const get_language = () => {
    axios.get('/api/greetings/language')
        .then((response) => {
            let results = response.data
            let data = results;
            console.log(data, "this");
            let displayHtml = dropdownCompiler({ lang: data })
            dropdownData.innerHTML = displayHtml;
            

        })
}

async function greet() {
    let userName = nameInput.value;
    let languageSelected = language.value;

    let params = {
        "userName": userName,
        "language": languageSelected
    }

    await axios.post('/api/greetings/greet  ', params)
}

greetButton.addEventListener('click', function () {
    greet()
})


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
    get_names();
    get_language();
})
