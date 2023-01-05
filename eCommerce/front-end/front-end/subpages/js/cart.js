function getfromlocalToCart(){
    let data=localStorage.getItem("cart");
    data=JSON.parse(data);
    document.getElementById("cartwala").innerText=data.totalItem;
}
getfromlocalToCart();




function display(){
    let bag=` <div class="basket-module">
    <label for="promo-code" style="margin-left: 13px;
    font-size: 18px; font-weight:bolder;">Enter a promotional code</label>
    <input id="promo-code" type="text" name="promo-code" maxlength="5" class="promo-code-field" placeholder="Enter Your Cupon Code">
    <button class="promo-code-cta">Apply</button>
  </div>
  
  <div class="basket-labels">
    <ul>
      <li class="item item-heading">Item</li>
      <li class="price">Price</li>
      <li class="quantity">Quantity</li>
      <li class="subtotal">Subtotal</li>
    </ul>
  </div>>`
    let value=localStorage.getItem("cart");
    value=JSON.parse(value)
    let allProduct=value.products;
    allProduct.forEach(element => {
        console.log(element)
        bag+=`  <div class="basket-product">
        <div class="item">
          <div class="product-image">
            <img src="http://placehold.it/120x166" alt="Placholder Image 2" class="product-frame">
          </div>
          <div class="product-details">
            <h5 style="font-size: 1.1rem;">Lace Sleeve Cuff Dress</h5>
            <p><strong>Navy, Size 18</strong></p>
            <p>Product Code - 232321939</p>
          </div>
        </div>
        <div class="price">26.00</div>
        <div class="quantity">
          <input type="number" value="4" min="1" class="quantity-field">
        </div>
        <div class="subtotal">104.00</div>
        <div class="remove">
          <button>Remove</button>
        </div>
      </div>
     
    </div`
    });
    document.getElementById("containercart").innerHTML=bag;
}
display();