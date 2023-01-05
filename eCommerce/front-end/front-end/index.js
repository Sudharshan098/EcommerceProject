// // let datas=0;
// function hi(){
//     fetch('http://localhost:8080/user/products')
// .then((response) => response.json())
// .then((data) => hello(data));

// }
// hi();
// {/*  */}
// function hello(data){
//     let container= document.getElementById("mainct");
//     let bag="";
//     // console.log(data);
//     data.forEach(element => {
//         console.log(element)
//         bag+=`<div class="card" style="width: 18rem;">
//         <img src="${element.image}" class="card-img-top" alt="...">
//         <div class="card-body">
//           <h5 class="card-title">${element.productName}</h5>
//           <p class="card-text">${element.desc}</p>
//           <a href="#" class="btn btn-primary">Add To Cart</a>
//         </div>
//         </div>`
//     });
//     document.getElementById("mainct").innerHTML=bag;
// }
let array;
    function getData(){
    fetch('http://localhost:8080/user/products')
    .then((res)=> res.json())
    .then((res)=>{
        array=res;
        display(res);
    })
    .catch((eror)=>{
        console.log(eror)
    })
}
function display(data){
    let container=document.querySelector('.container');
    container.innerHTML=''
    data.forEach(element => {
        // console.log(element)
        let div=document.createElement('div')
        let img=document.createElement('img');
        img.src=element.image;
        let p=document.createElement('p');
        p.innerText="Title: "+element.productName;
        let p2=document.createElement('p');
        p2.innerText="Price: ₹"+element.price;
        let btn=document.createElement('button');
        let p3=document.createElement('p');
        p3.innerText="Desc:- "+element.desc;
        let p4=document.createElement('p');
        p4.innerText="Rating: "+element.rating;       
        btn.innerText="Add To Cart"
        btn.addEventListener('click',function(){
            console.log(element)
            addToCart(element)
        })
        div.append(img,p,p2,p3,p4,btn);
        container.append(div);
        
    });
}
getData();

function addToCart(element){
    fetch(`http://localhost:8080/user/product/add`,{
        method:"POST",
        headers:{
            "Content-type":"application/json",
            'token':localStorage.getItem("token")
        },
        body:JSON.stringify(element)
    })
    .then(res=> res.json())
    .then(data=> {
        localStorage.setItem("cart",JSON.stringify(data));
        alert("You add"+data.totalItem+"")
        getfromlocalToCart();
    })
}
function getfromlocalToCart(){
    let data=localStorage.getItem("cart");
    data=JSON.parse(data);
    document.getElementById("cartwala").innerText=data.totalItem;
}
getfromlocalToCart();