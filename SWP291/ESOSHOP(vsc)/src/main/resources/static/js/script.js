var counter = 1;
setInterval(function () {
    document.getElementById('radio' + counter).checked = true;
    counter++;
    if (counter > 4) {
        counter = 1;
    }
}, 7000)






const stars = document.querySelectorAll('.star');
const ratingInput = document.getElementById('rating');
const ratingForm = document.getElementById('ratingForm');

stars.forEach((star, index) => {
    star.addEventListener('click', () => {
        const selectedValue = index + 1;
        ratingInput.value = selectedValue;

        // Làm vàng tất cả các ngôi sao đứng trước ngôi sao được chọn
        stars.forEach((s, i) => {
            if (i <= index) {
                s.classList.add('selected');
            } else {
                s.classList.remove('selected');
            }
        });
    });
});

ratingForm.addEventListener('submit', (event) => {
    event.preventDefault();
    // Điều này sẽ gửi giá trị đánh giá đến server.
});



const checkboxes = document.querySelectorAll('.checkbox');
const quantities = document.querySelectorAll('.quantity');
const unitPrices = document.querySelectorAll('.unit-price');
const totalElement = document.getElementById('total');
const shipElement = document.getElementById('shipTotal');
const tempElement = document.getElementById('tempTotal');

checkboxes.forEach((checkbox, index) => {
    checkbox.addEventListener('change', () => {
        updateTotal();
    });
});

quantities.forEach((quantityInput, index) => {
    quantityInput.addEventListener('input', () => {
        updateTotal();
    });
});

function updateTempTotal() {
    let tempTotal = 0;
    for (let i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) {
            const quantity = parseInt(quantities[i].value);
            const unitPrice = parseFloat(unitPrices[i].textContent);
            tempTotal += quantity * unitPrice;
        }
    }
    tempElementElement.textContent = `${tempTotal.toFixed(2)}`;
}

function updateTotal(){
    let total = 0;
    total = parseFloat(tempElement.textContent) + parseFloat(shipElement.textContent)
    totalElement.textContent = `${total.toFixed(2)}`;
}