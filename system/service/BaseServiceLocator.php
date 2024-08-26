<?php
/**

 * User: lanton
 * Date: 2024/08/25
 * Time: 5:12 PM
 */

declare(strict_types=1);

namespace wyzetech\shabisa\system\Service;

use wyzetech\shabisa\system\Service;

abstract class BaseServiceLocator {
    abstract function find(string $name) : Service;
}
