<?php

declare(strict_types=1);

namespace wyzetech\shabisa\application\user\controller;
use wyzetech\shabisa\system\Controller;

class SigninController extends Controller {
    public function index() {
        $this->render("user/signin");
    }
}
