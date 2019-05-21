package main

import (
	"github.com/hyperledger/fabric/core/chaincode/shim"
	sc "github.com/hyperledger/fabric/protos/peer"
	"fmt"
	"encoding/json"
)

type SmartContract struct {
}

type transaction struct {
	PaymentInstitutionID string `json:"payment_institution_id"`
	PaymentUserID string `json:"payment_user_id"`
	CollectionInstitutionID string `json:"collection_institution_id"`
	CollectionUserID string `json:"collection_user_id"`
	DateTime string `json:"date_time"`
	Type string `json:"type"`
	Sum string `json:"sum"`
}

type balanceChange struct {
	InstitutionID string `json:"institution_id"`
	UserID string `json:"user_id"`
	DateTime string `json:"date_time"`
	Type string `json:"type"`
	Sum string `json:"sum"`// negative for withdraw, positive for top
}


func (s *SmartContract) Init(APIstub shim.ChaincodeStubInterface) sc.Response  {
	return shim.Success(nil)

}

func (s *SmartContract) Invoke(APIstub shim.ChaincodeStubInterface) sc.Response {
	function, args := APIstub.GetFunctionAndParameters()

	if function == "createTransaction" {
		return s.createTransaction(APIstub,args)
	}else if function == "queryTransaction" {
		return s.queryTransaction(APIstub,args)
	}else if function == "initLedger" {
		return s.initLedger(APIstub)
	}else if function == "queryBalanceChange" {
		return s.queryBalanceChange(APIstub,args)
	}else if function == "createBalanceChange"{
		return s.createBalanceChange(APIstub,args)
	}
	return shim.Error("Invalid Smart Contract function name.")
}

func (s *SmartContract) initLedger(APIstub shim.ChaincodeStubInterface) sc.Response {
	return shim.Success(nil)
}

func (s *SmartContract) createTransaction (APIstub shim.ChaincodeStubInterface, args []string) sc.Response  {
	if len(args) != 8{
		return shim.Error("Incorrect number of arguments. Excepting 8")
	}
	var trans = transaction{PaymentInstitutionID: args[1],PaymentUserID: args[2],
		CollectionInstitutionID: args[3],CollectionUserID: args[4],
		DateTime: args[5],Type:args[6],Sum: args[7]}
	fmt.Println(trans)
	transAsBytes, hhhh := json.Marshal(trans)
	fmt.Println(hhhh)
	fmt.Println(transAsBytes)
	err := APIstub.PutState(args[0],transAsBytes)
	fmt.Println(err)
	return shim.Success(nil)
}

func (s *SmartContract) queryTransaction (APIstub shim.ChaincodeStubInterface, args []string) sc.Response  {
	if len(args) != 1{
		return shim.Error("Incorrect number of arguments. Excepting 1")
	}
	transAsBytes, _ := APIstub.GetState(args[0])
	return shim.Success(transAsBytes)
}

func (s *SmartContract) queryBalanceChange (APIstub shim.ChaincodeStubInterface, args []string) sc.Response  {
	if len(args) != 1 {
		return shim.Error("Incorrect number of arguments. Expecting 1")
	}
	changeAsBytes, _ := APIstub.GetState(args[0])
	return shim.Success(changeAsBytes)
}

func (s *SmartContract) createBalanceChange (APIstub shim.ChaincodeStubInterface, args []string) sc.Response  {
	if len(args) != 6 {
		return shim.Error("Incorrect number of arguments. Expecting 6")
	}
	var change = balanceChange{InstitutionID:args[1],UserID:args[2],DateTime:args[3],Type:args[4],Sum:args[5]}
	fmt.Println(change)
	changeAsBytes, _ := json.Marshal(change)
	APIstub.PutState(args[0],changeAsBytes)
	return shim.Success(nil)
}


func main() {
	err := shim.Start(new(SmartContract))
	if err != nil{
		fmt.Printf("Error creating new Smart Constract: %s",err)
	}
}