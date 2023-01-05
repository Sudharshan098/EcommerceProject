
document.getElementById("createAccount").addEventListener("click",function(){
    event.preventDefault();
    let name=document.getElementById("name").value
    let email=document.getElementById("email").value
    let mobile=document.getElementById("mobile").value
    let address=document.getElementById("address").value
    let password=document.getElementById("password").value
    let obj={
        customerName:name,
        email:email,
        mobileNo:mobile,
        password:password,
        address:address
    }
    getData(obj);  
    function getData(obj){
        fetch('http://localhost:8080/customer/reagister',{
            method:"POST",
            headers: {
                'Content-Type': 'application/json'
              },
              body:JSON.stringify(obj)
        })
        .then((res)=> res.json())
        .then((res)=>{
            localStorage.setItem("user",JSON.stringify(res))
            console.log(res);
        })
        .catch((eror)=>{
            console.log(eror+"eroor")
        })
    }
    
})
function getfromlocalToCart(){
    let data=localStorage.getItem("cart");
    data=JSON.parse(data);
    document.getElementById("cartwala").innerText=data.totalItem;
}
getfromlocalToCart();