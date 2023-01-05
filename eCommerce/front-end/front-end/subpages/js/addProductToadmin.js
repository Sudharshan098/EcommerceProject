function addCategoryToOption(){
    fetch("http://localhost:8080/categorys")
    .then(res=> res.json())
    .then(data=>{
        mergerCategory(data)
    })
    .catch(error=>{
        alert(error)
    })
}
function mergerCategory(data){
     let bag="<option selected >Select Category</option>" 
        data.forEach(element => {
            bag+=` <option value=${element.categoryName}>${element.categoryName}</option>`
        });
        document.getElementById("category").innerHTML=bag;
         

}
addCategoryToOption();

// upload file function 
document.getElementById("uploadfile").addEventListener("click",function(){
    event.preventDefault();
    let files=document.getElementById("productImage");
    let fileData=files.files[0];
    
    let formd=new FormData();
    formd.append('file',fileData)
    // console.log(Json)
    fetch(`http://localhost:8080/admin/product/file`,{
        method:"POST",
        headers:{
            'token':localStorage.getItem("token")
        },
        body:formd
    })
    .then(res=>res.json())
    .then(res=>{
        document.getElementById("filemsg").innerHTML=res.key;
    })

})
document.getElementById("uploadData").addEventListener("click",function(){
    event.preventDefault()
    let productName=document.getElementById("ProductName").value;
    let productPrice=+document.getElementById("productPrice").value
    let productRating=+document.getElementById("ProductRating").value
    let productQuantity=+document.getElementById("ProductQuantity").value;
    let productDesc=document.getElementById("ProductDesc").value;
    let category=document.getElementById("category").value
    let images=document.getElementById("productImage").files[0].name;
    let obj={
        productName:productName,
        quantity:productQuantity,
        rating:productRating,
        price:productPrice,
        desc:productDesc,
        image:images,
        category:{
            categoryName:category
        },
      }
    console.log(obj);
    productData(obj)
    
})
function productData(obj){
    fetch(`http://localhost:8080/admin/product`,{
        method:"POST",
        headers:{
            "Content-type":"application/json",
            'token':localStorage.getItem("token")
        },
        body:JSON.stringify(obj)
        
    })
    .then(res=>res.json())
    .then(data=>{
        console.log(data)
    })
}
function getfromlocalToCart(){
    let data=localStorage.getItem("cart");
    data=JSON.parse(data);
    document.getElementById("cartwala").innerText=data.totalItem;
}
getfromlocalToCart();