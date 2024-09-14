<?php


$ffi = FFI::cdef("int Add(int a, int b);", __LIB_DIR__ . DIRECTORY_SEPARATOR . "libmylib.so");

$result = $ffi->Add(5, 7);

echo "<pre />FFI result: $result </pre>";

$ffi = null;