<?php $__env->startSection('content'); ?>

<div class="p-5 mb-4 bg-body-tertiary rounded-3">
    <div class="container-fluid py-1">
        <h1 class="display-5 fw-bold">Welcome to iSec</h1>
        <p class="col-md-8 fs-4">iSec aims to be your one-stop-shop for all security related needs.
            Continue to browse to learn more.<br />
        If you have not registered, click on the register button below to
            create your profile.<br />
        If you already have registered, that is, you already have a profile, click sign in to enjoy
        your benefits of being a registered member.
        </p>
        <a class="btn btn-primary btn-lg" type="button" href="/user/signin">Sign In</a>
        <a class="btn btn-primary btn-lg" type="button" href="/user/register">Register</a>
    </div>
</div>
<?php $__env->stopSection(); ?>
<?php echo $__env->make('layout/main', \Illuminate\Support\Arr::except(get_defined_vars(), ['__data', '__path']))->render(); ?><?php /**PATH /home/lanton/projects/xabisa/views/index.blade.php ENDPATH**/ ?>