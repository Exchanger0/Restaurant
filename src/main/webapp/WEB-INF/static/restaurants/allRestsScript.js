const elements = document.querySelectorAll('.rest');

let maxWidth = 0;
elements.forEach(element => {
  const elementWidth = element.scrollWidth;
  if (elementWidth > maxWidth) {
    maxWidth = elementWidth;
  }
});

elements.forEach(element => {
  element.style.width = `${maxWidth}px`;
});