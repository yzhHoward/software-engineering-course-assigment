// 参考https://www.cnblogs.com/studyzy/p/7360733.html和之前的go文件编写
// 这个go文件叫什么、放在哪里都无所谓，在Java里调用即可
package main

import (
   "github.com/hyperledger/fabric/core/chaincode/shim"
   pb "github.com/hyperledger/fabric/protos/peer"
   "fmt"
)

type SimpleChaincode struct {
}

func main() {
   err := shim.Start(new(SimpleChaincode))
   if err != nil {
      fmt.Printf("Error starting Simple chaincode: %s", err)
   }
}
func (t *SimpleChaincode) Init(stub shim.ChaincodeStubInterface) pb.Response {
   return shim.Success(nil)
}


func (t *SimpleChaincode) Invoke(stub shim.ChaincodeStubInterface) pb.Response {
   function, args := stub.GetFunctionAndParameters()
   fmt.Println("invoke is running " + function)
   if function == "test1" {//自定义函数名称
      return t.test1(stub, args)//定义调用的函数
   }
   return shim.Error("Received unknown function invocation")
}
func (t *SimpleChaincode) test1(stub shim.ChaincodeStubInterface, args []string) pb.Response{
   return shim.Success([]byte("Called test1"))
}