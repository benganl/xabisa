<?php

ini_set('error_reporting', E_ALL);
ini_set('display_errors', 1);  // Off in production

$file = __FILE__;

$app_dir = dirname($file);
define('__APP_DIR__', $app_dir);

$vendor_dir = __APP_DIR__ . DIRECTORY_SEPARATOR . "vendor";
define('__VENDOR_DIR__', $vendor_dir);

$sys_dir = __APP_DIR__ . DIRECTORY_SEPARATOR . "system";
define('__SYS_DIR__', $sys_dir);

$lib_dir = $sys_dir . DIRECTORY_SEPARATOR . "lib";
define('__LIB_DIR__', $lib_dir);

$cache_dir = __APP_DIR__ . DIRECTORY_SEPARATOR . "cache";
define('__CACHE_DIR__', $cache_dir);

$view_dir = __APP_DIR__ . DIRECTORY_SEPARATOR . "views";
define('__VIEW_DIR__', $view_dir);

$view_cache_dir = __CACHE_DIR__ . DIRECTORY_SEPARATOR . "view";
define('__VIEW_CACHE_DIR__', $view_cache_dir);


require_once $vendor_dir . DIRECTORY_SEPARATOR . 'autoload.php';


use xabisa\sys\RequestBuilder;
use xabisa\sys\Application;


header("Cache-Control: must-revalidate, max-age=31536000, public");
header("Expires: " . gmdate("D, d M Y H:i:s", time() + 31536000) . " GMT");
header("text/html; charset=UTF-8");
header("x-powered-by: WyzeTech Platform 1.0.0");


$rb = new RequestBuilder();
$request = $rb->build();

$application = new Application();
$application->run($request);
