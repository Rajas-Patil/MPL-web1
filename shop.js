// Sample product data
const products = [
    { id: 1, name: 'Product 1', price: 29.99, image: 'https://via.placeholder.com/150' },
    { id: 2, name: 'Product 2', price: 39.99, image: 'https://via.placeholder.com/150' },
    { id: 3, name: 'Product 3', price: 19.99, image: 'https://via.placeholder.com/150' },
];

const productList = document.getElementById('product-list');
const cartList = document.getElementById('cart-list');
let cart = [];

// Function to render products on the page
function displayProducts() {
    products.forEach(product => {
        const productItem = document.createElement('div');
        productItem.classList.add('product-item');

        productItem.innerHTML = `
            <img src="${product.image}" alt="${product.name}">
            <h3>${product.name}</h3>
            <p>$${product.price}</p>
            <button onclick="addToCart(${product.id})">Add to Cart</button>
        `;
        productList.appendChild(productItem);
    });
}

// Function to add products to the cart
function addToCart(productId) {
    const product = products.find(p => p.id === productId);
    cart.push(product);

    updateCart();
}

// Function to update cart display
function updateCart() {
    cartList.innerHTML = '';
    cart.forEach(item => {
        const cartItem = document.createElement('li');
        cartItem.textContent = `${item.name} - $${item.price}`;
        cartList.appendChild(cartItem);
    });
}

// Initialize the product display
displayProducts();


// Add to Cart function
function addToCart(productName, productPrice) {
    alert(`${productName} added to cart for $${productPrice}`);
    // You can add more logic here to handle cart functionality.
    // For example, you can update the cart in local storage or send it to the backend.
  }
  
  // Buy Now function
  function buyNow(productName, productPrice) {
    alert(`Proceed to buy ${productName} for $${productPrice}`);
    // Add logic here to redirect to checkout or initiate payment.
  }
  


// Add to Cart function
function addToCart(productName, productPrice) {
    alert(`${productName} added to cart for $${productPrice}`);
    // Simulate cart update (here we use local storage for demo purposes)
    let cart = JSON.parse(localStorage.getItem('cart')) || [];
    cart.push({ productName, productPrice });
    localStorage.setItem('cart', JSON.stringify(cart));
  
    // Redirect to a cart page (or simulate this)
    window.location.href = "cart.html";
  }
  
  // Buy Now function
  function buyNow(productName, productPrice) {
    alert(`Proceed to buy ${productName} for $${productPrice}`);
    // In a real-world app, this would redirect to the checkout page
    window.location.href = `checkout.html?product=${encodeURIComponent(productName)}&price=${productPrice}`;
  }
  


  // JS for main page to add to cart and buy now

  // Add to Cart function
function addToCart(productName, productPrice) {
    let cart = JSON.parse(localStorage.getItem('cart')) || [];
    cart.push({ productName, productPrice });
    localStorage.setItem('cart', JSON.stringify(cart));
    alert(`${productName} has been added to the cart!`);
    window.location.href = "cart.html"; // Redirect to cart page
  }
  
  // Buy Now function
  function buyNow(productName, productPrice) {
    window.location.href = `product-details.html?product=${encodeURIComponent(productName)}&price=${productPrice}`;
  }
  
