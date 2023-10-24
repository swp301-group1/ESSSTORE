  // JavaScript
  const blackImageInput = document.getElementById('black-image-input');
  const whiteImageInput = document.getElementById('white-image-input');
  const redImageInput = document.getElementById('red-image-input');

  const blackImagePreview = document.getElementById('black-image-preview');
  const whiteImagePreview = document.getElementById('white-image-preview');
  const redImagePreview = document.getElementById('red-image-preview');

  // Function to update the image preview
  function updateImagePreview(input, preview) {
      if (input.files && input.files[0]) {
          const reader = new FileReader();
          reader.onload = function (e) {
              preview.src = e.target.result;
              preview.style.display = 'block';
          };
          reader.readAsDataURL(input.files[0]);
      }
  }

  // Add event listeners to input elements
  blackImageInput.addEventListener('change', function () {
      updateImagePreview(this, blackImagePreview);
  });

  whiteImageInput.addEventListener('change', function () {
      updateImagePreview(this, whiteImagePreview);
  });

  redImageInput.addEventListener('change', function () {
      updateImagePreview(this, redImagePreview);
  });