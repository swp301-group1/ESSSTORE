document.addEventListener("DOMContentLoaded", function () {
    // Lấy các phần tử HTML
    const selectBrand = document.getElementById("selectColor");
    const selectedImage = document.getElementById("selectedImage");
    const uploadImage = document.getElementById("uploadImage");
    const uploadButton = document.getElementById("uploadButton");

    // Xử lý khi chọn màu
    selectBrand.addEventListener("change", function () {
        // Lấy giá trị được chọn
        const selectedColor = selectBrand.value;

        // Tùy chỉnh ảnh dựa trên màu được chọn
        switch (selectedColor) {
            case "1": // Red
                selectedImage.src = "/img/iphone.png"; // Đặt đường dẫn của ảnh tương ứng
                break;
            case "2": // White
                selectedImage.src = "/img/phone.png"; // Đặt đường dẫn của ảnh tương ứng
                break;
            case "3": // Black
                selectedImage.src = "/img/oppo.png"; // Đặt đường dẫn của ảnh tương ứng
                break;
            default:
                selectedImage.src = ""; // Đặt ảnh mặc định nếu không có màu nào được chọn
                break;
        }

        // Hiển thị nút tải lên ảnh
        uploadButton.style.display = "block";
    });

    // Xử lý khi nhấp vào nút tải lên ảnh
    uploadButton.addEventListener("click", function () {
        // Khi nút tải lên ảnh được nhấp, kích hoạt sự kiện tải lên ảnh từ máy tính
        uploadImage.click();
    });

    // Xử lý khi chọn tệp ảnh từ máy tính
    uploadImage.addEventListener("change", function () {
        // Xử lý tải lên ảnh và cập nhật ảnh hiển thị
        const selectedFile = uploadImage.files[0];
        if (selectedFile) {
            const imageURL = URL.createObjectURL(selectedFile);
            selectedImage.src = imageURL;
        }
    });
});
