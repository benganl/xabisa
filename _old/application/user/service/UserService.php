<?php
/**
 * User: lanton
 * Date: 2024/08/26
 * Time: 3:34 AM
 */
declare(strict_types=1);

namespace wyzetech\shabisa\application\user\service;

use wyzetech\shabisa\system\context\ExecutionContext;
use wyzetech\shabisa\system\Service;

class UserService extends Service
{
    public function execute(ExecutionContext $executionContext) : void
    {
        echo '<br />Hello from UserService<br />';
    }
}
