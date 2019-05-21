// 参考 https://www.cnblogs.com/studyzy/p/7360733.html 和之前的go文件编写
// 这个go文件叫什么、放在哪里都无所谓，在Java里调用即可
package main

import (
	"fmt"

	"github.com/hyperledger/fabric/core/chaincode/shim"
	pb "github.com/hyperledger/fabric/protos/peer"
)

// User defined chain code struct
type SimpleChaincode struct{}

func (t *SimpleChaincode) Init(stub shim.ChaincodeStubInterface) pb.Response {
	return shim.Success(nil)
}

func (t *SimpleChaincode) Invoke(stub shim.ChaincodeStubInterface) pb.Response {
	function, args := stub.GetFunctionAndParameters()
	fmt.Println("invoke is running " + function)

	if function == "test1" {
		return t.test1(stub, args)
	} else {
		return shim.Error("Received unknown function invocation")
	}
}

func (t *SimpleChaincode) test1(stub shim.ChaincodeStubInterface, args []string) pb.Response {
	return shim.Success([]byte("Called test1"))
}

// End User defined chain code struct

// User defined smart contract
type SmartContract struct{}

func (t *SmartContract) insert_record(stub shim.ChaincodeStubInterface, args []string) string {
	// TODO: Implement insert_record function
	return "key of inserted record"
}

func (t *SmartContract) update_record(stub shim.ChaincodeStubInterface, args []string) pb.Response {
	// TODO: Implement update_record
	return shim.Success([]byte("Call update"))
}

func (t *SmartContract) delete_record(stud shim.ChaincodeStubInterface, args []string) pb.Response {
	// TODO: Implement delete_record
	return shim.Success([]byte("Call delete"))
}

// End user defined smart contrct

// Data type
type Transaction struct {
	DateTime string `json:"date_time"`
	Type     string `json:"sum"`
}

func (t *Transaction) generate_key() string {
	// TODO: implement key generator function, return value must be unique for every different instance
	// and be constant to same Transaction object
	return "dumb_key"
}

// End date type

func main() {
	err := shim.Start(new(SimpleChaincode))
	if err != nil {
		fmt.Printf("Error starting Simple chaincode: %s", err)
	}
}
