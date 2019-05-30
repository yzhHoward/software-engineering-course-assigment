package main

import (
	"encoding/json"
	"fmt"

	"github.com/hyperledger/fabric/core/chaincode/shim"
	pb "github.com/hyperledger/fabric/protos/peer"
)

type SmartContract struct{}

// Data type
type Record struct {
	EncrypedMessage string `json:"encryped_message"`
}
type UserInfo struct {
	EncrypedMessage string `json:"encryped_message"`
}

// End date type

// End User defined chain code struct

// User defined smart contract
func (s *SmartContract) Init(APIstub shim.ChaincodeStubInterface) pb.Response {
	return shim.Success(nil)
}

func (s *SmartContract) Invoke(APIstub shim.ChaincodeStubInterface) pb.Response {
	function, args := APIstub.GetFunctionAndParameters()

	if function == "insert_user_info" {
		return s.insert_user_info(APIstub, args)
	} else if function == "query_user_info" {
		return s.query_user_info(APIstub, args)
	} else if function == "query_contract_record" {
		return s.query_record(APIstub, args)
	} else if function == "query_financing_apply_record" {
		return s.query_record(APIstub, args)
	} else if function == "query_loan_record" {
		return s.query_record(APIstub, args)
	} else if function == "query_repayment_record" {
		return s.query_record(APIstub, args)
	} else if function == "insert_contract_record" {
		return s.insert_record(APIstub, args)
	} else if function == "insert_financing_apply_record" {
		return s.insert_record(APIstub, args)
	} else if function == "insert_loan_record" {
		return s.insert_record(APIstub, args)
	} else if function == "insert_repayment_record" {
		return s.insert_record(APIstub, args)
	} else if function == "initedger" {
		return s.initLedger(APIstub)
	}
	return shim.Error("Invalid Smart Contract function name")
}

func (s *SmartContract) initLedger(APIstub shim.ChaincodeStubInterface) pb.Response {
	return shim.Success(nil)
}

func get_user_info_key(username string) string {
	return "<username>" + username
}

func get_record_key(record_id string) string {
	// return "<record_key>" + strconv.FormatInt(record_id, 10)
	return "<record_key>" + record_id
}

func (t *SmartContract) insert_user_info(stub shim.ChaincodeStubInterface, args []string) pb.Response {
	// args[0]: username
	// args[1]: message
	if len(args) != 2 {
		return shim.Error("Incorrect number of arguments. Expecting 2")
	}
	var user_info = UserInfo{
		EncrypedMessage: args[1]}
	fmt.Println(user_info)
	user_info_as_bytes, other_info := json.Marshal(user_info)
	fmt.Println(other_info)
	fmt.Println(user_info_as_bytes)
	err := stub.PutState(get_record_key(args[0]), user_info_as_bytes)
	fmt.Println(err)
	return shim.Success(nil)
}

func (t *SmartContract) query_user_info(stub shim.ChaincodeStubInterface, args []string) pb.Response {
	// args[0]: username
	if len(args) != 1 {
		return shim.Error("Incorrect number of arguments. Excepting 1")
	}
	user_info_as_bytes, _ := stub.GetState(get_record_key(args[0]))
	return shim.Success(user_info_as_bytes)
}

func (t *SmartContract) insert_record(stub shim.ChaincodeStubInterface, args []string) pb.Response {
	// args[0]: record_id
	// args[1]: message
	if len(args) != 2 {
		return shim.Error("Incorrect number of arguments. Expecting 2")
	}
	var record = Record{
		EncrypedMessage: args[1]}
	fmt.Println(record)
	user_info_as_bytes, other_info := json.Marshal(record)
	fmt.Println(other_info)
	fmt.Println(user_info_as_bytes)
	err := stub.PutState(get_user_info_key(args[0]), user_info_as_bytes)
	fmt.Println(err)
	return shim.Success(nil)
}

func (t *SmartContract) query_record(stub shim.ChaincodeStubInterface, args []string) pb.Response {
	// args[0]: record_id
	if len(args) != 1 {
		return shim.Error("Incorrect number of arguments. Excepting 1")
	}
	record_as_bytes, _ := stub.GetState(get_record_key(args[0]))
	return shim.Success(record_as_bytes)
}

// End user defined smart contrct

func main() {
	err := shim.Start(new(SmartContract))
	if err != nil {
		fmt.Printf("Error starting Simple chaincode: %s", err)
	}
}

// 参考 https://www.cnblogs.com/studyzy/p/7360733.html 和之前的go文件编写
// 这个go文件叫什么、放在哪里都无所谓，在Java里调用即可
