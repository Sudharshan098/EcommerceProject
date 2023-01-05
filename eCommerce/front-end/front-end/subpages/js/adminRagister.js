document.getElementById("ragisterAcoount").addEventListener("click",function(){
    event.preventDefault();
   
    let name=document.getElementById("name").value
    let email=document.getElementById("email").value;
    let password=document.getElementById("password").value;

    let obj={
        name:name,
        email:email,
        password:password

    }
    login(obj)
    console.log(obj)
})
function login(obj){
    let mastervlaue=prompt("Enter the Master key")
    fetch("http://localhost:8080/admin/ragister",{
        method:"POST",
        headers:{
            'Content-Type': 'application/json',
            'token':mastervlaue
        },
        body:JSON.stringify(obj)

    })
    .then(res => res.json())
    .then(data=>{
        localStorage.setItem("token",data.key);
        localStorage.setItem("user",JSON.stringify(obj))
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