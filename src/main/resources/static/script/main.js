$('.radio-td label').click(() => {
    $('.radio-td label').each((index, elem) => {
        if (elem.firstElementChild.checked) {
            elem.parentElement.parentElement.classList.add("checked");
        } else {
            elem.parentElement.parentElement.classList.remove("checked");
        }
    });
});