const nameInput = document.querySelector('.name');
const language = document.querySelector('.language');
const greetButton = document.querySelector('.greet');
const greetingBox = document.querySelector('.content');
const homeUrl = document.querySelector('.homeUrl');
const greetedUrl = document.querySelector('.greetedUrl');


const dropdown = document.querySelector('.dropdownLanguage').innerHTML;
const dropdownCompiler = Handlebars.compile(dropdown);
const dropdownData = document.querySelector('.languageDrop');

const pageContent = document.querySelector('.greetingsMain');
const displayPhrase = document.querySelector('.greetins');


const greetedNames = document.querySelector('.greetedNames');
const greetedNamesCompiler = Handlebars.compile(greetedNames.innerHTML);

const home = document.querySelector('.greetingsHomePage');
const homeCompiler = Handlebars.compile(home.innerHTML);

const greetingMessage = document.querySelector('.message');
const greetMsgCompiler = Handlebars.compile(greetingMessage.innerHTML);

const pagingTemplate = document.querySelector('.paging');
const pagingCompiler = Handlebars.compile(pagingTemplate.innerHTML);



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

const sortPagination = (rowsCount) => {
    return Math.ceil(rowsCount / 3);
}

const get_names = () => {
    axios.get('/api/greeted/names', { api_call_time: new Date().getTime() })
        .then((response) => {
            let userName = response.data;
            console.log(response);
            
            let rowsCount = 0;

            let showHtml = greetedNamesCompiler({ names: userName });
            pageContent.innerHTML = showHtml;
            const loader = document.getElementById('tableLoader');
            const page = document.querySelector('.pagerContent');


            for (let x = 0; x < userName.length; x++) {
                const element = userName[0].rowsCount;
                rowsCount = element
            }

            if (rowsCount !== 0) {
                let numbersArray = []
                for (let pages = 0; pages < sortPagination(rowsCount); pages++) {
                    const element = pages;
                    numbersArray.push(element)

                    let displayPegination = pagingCompiler({ pagination: numbersArray });
                    page.innerHTML = displayPegination;
                }
                console.log(numbersArray, "Numbers array");
            }



            if (response) {
                greetingBox.classList.add('hide');
                loader.classList.add('active');
            } if (response.status === 200) {
                setTimeout(() => {
                    loader.classList.remove('active');
                    greetingBox.classList.remove('hide');
                }, onLoadEventHandler(response.config.api_call_time));
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

const greet = async () => {
    let userName = nameInput.value;
    let languageSelected = language.value;
    let params = {
        "userName": userName,
        "language": languageSelected,
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
    console.log(url[1]);
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


$(document).ready(() => {
    $('.ui.dropdown').dropdown();
    setTimeout(function () {
        $('#dimm').hide();
    }, onLoadEventHandler(pageLoadingTime))

    $(document).ready(() => {
        $('.ui.accordion').accordion();
    });
    get_language();
    buildHomePage();
});

greetButton.addEventListener('click', () => {
    greet()
})

// Handlebars.registerHelper('pagination', () => {
//     if (rowsCount === 0) {
//         return true;
//     }
// })


