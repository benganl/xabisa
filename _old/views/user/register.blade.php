@extends('layout/main')

@section('content')

<div class="p-5 mb-4 bg-body-tertiary rounded-3">
    <form action="/user/register" method="post">
        <div class="mb-3">
            <label for="emailAddress" class="form-label">Email Address</label>
            <input type="email" class="form-control" id="emailAddress" name="emailAddress" aria-describedby="emailHelp" />
            <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
        </div>
        <div class="mb-3">
            <label for="mobileNumber" class="form-label">Mobile Number</label>
            <input type="tel" class="form-control" id="mobileNumber" name="mobileNumber"
                aria-describedby="mobileNumberHelp">
            <div id="mobileNumberHelp" class="form-text">We'll never share your mobile number with anyone else.</div>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input type="password" class="form-control" id="password" name="password" />
        </div>
        <div class="mb-3">
            <label for="passwordConfirm" class="form-label">Confirm Password</label>
            <input type="password" class="form-control" id="passwordConfirm" />
        </div>
        <button type="submit" class="btn btn-primary">Register</button>
    </form>
</div>
@endsection
