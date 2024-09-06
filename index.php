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

use Jenssegers\Blade\Blade;
use wyzetech\shabisa\application\user\operation\UserCreateCommand;
use wyzetech\shabisa\system\context\ExecutionContext;
use wyzetech\shabisa\system\database\QueryParam;
use wyzetech\shabisa\system\service\ServiceProvider;
use wyzetech\shabisa\application\user\service\UserService;
use wyzetech\shabisa\system\Database;

$user = new \wyzetech\shabisa\application\user\models\User('0721234567', 'test@email.com', '123456');
echo '<pre>';
var_dump($user);
echo '</pre>';


$serviceProvider = ServiceProvider::getInstance();

$executionContext = new ExecutionContext();
$executionContext->setServiceLocator($serviceProvider);


$userService = new UserService();
$serviceProvider->register('userService', $userService);

$userCreateCmd = new UserCreateCommand();
$executionContext->execute($userCreateCmd);

echo '<pre>';
var_dump($serviceProvider);
echo '</pre>';

$userService = $serviceProvider->find('userService');
$userService->execute();

// phpinfo();

$db = Database::getInstance('dev', 'dev@c00l!');
$params = [
    new QueryParam(':username', 'admin'),
];
$users = $db->select('select * from users where username = :username', $params);

echo '<pre>';
print_r($users);
echo '</pre>';


$ffi = FFI::cdef("int Add(int a, int b);", __LIB_DIR__ . DIRECTORY_SEPARATOR . "libmylib.so");

$result = $ffi->Add(5, 7);

echo "<br />FFI result: $result <br />";

$ffi = null;

$blade = new Blade($view_dir, $view_cache_dir);
echo $blade->render("index");
