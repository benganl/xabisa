package main

import (
	"C"
)

//export Add
func Add(a, b C.int) C.int {
	return a + b
}

func main() {}
