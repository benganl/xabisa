<?php
use wyzetech\shabisa\application\user\operation\UserCreateCommand;
use wyzetech\shabisa\system\context\ExecutionContext;

ini_set('error_reporting', E_ALL);
ini_set('display_errors', 1);  // Off in production

$file = __FILE__;

$app_dir = dirname($file);
define('__APP_DIR__', $app_dir);

$view_dir = $app_dir . DIRECTORY_SEPARATOR . "views";
define('__VIEW_DIR__', $view_dir);

$vendor_dir = $app_dir . DIRECTORY_SEPARATOR . "vendor";
define('__VENDOR_DIR__', $vendor_dir);

require_once $vendor_dir . DIRECTORY_SEPARATOR . 'autoload.php';

$user = new \wyzetech\shabisa\application\user\models\User('0721234567', 'test@email.com', '123456');
echo $user;


use wyzetech\shabisa\system\service\ServiceContext;
use wyzetech\shabisa\system\service\ServiceLocator;
use wyzetech\shabisa\system\service\ServiceRegistry;
use wyzetech\shabisa\application\user\service\UserService;
use wyzetech\shabisa\system\Database;

$serviceRegistry = ServiceRegistry::getInstance();
$serviceLocator = ServiceLocator::getInstance($serviceRegistry);


$serviceContext = ServiceContext::builder()
    ->withServiceLocator($serviceLocator)
    ->build();


$userService = new UserService();
call_user_func_array([$userService, "setServiceLocator"], [$serviceLocator]);


$serviceRegistry->register("userService", $userService);
$serviceRegistry->register("userService", $userService);
$serviceRegistry->register("userService", $userService);

echo '<pre>';
print_r($serviceRegistry);
echo '</pre>';

$userService = $serviceLocator->find('userService');
$userService->execute();

phpinfo();

$db = Database::getInstance('dev', 'dev@c00l!');
$db->connect();
$users = $db->query('select * from users');


$userCreateCmd = new UserCreateCommand();
$executionContext = new ExecutionContext();

$executionContext->execute($userCreateCmd);

