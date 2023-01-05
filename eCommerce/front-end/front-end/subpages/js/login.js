document.getElementById("loginAcoount").addEventListener("click",function(){
    event.preventDefault();
    let email=document.getElementById("email").value;
    let password=document.getElementById("password").value;

    let obj={
        email:email,
        password:password
    }
    login(obj)
})
function login(obj){

    fetch("http://localhost:8080/customer/login",{
        method:"POST",
        headers:{
            'Content-Type': 'application/json'
        },
        body:JSON.stringify(obj)

    })
    .then(res => res.json())
    .then(data=>{
        localStorage.setItem("token",data.key);
        console.log(data)
    })
    .catch(e=>{
        console.log(e)
    })
}
function getfromlocalToCart(){
    let data=localStorage.getItem("cart");
    data=JSON.parse(data);
    document.getElementById("cartwala").innerText=data.totalItem;
}
getfromlocalToCart();