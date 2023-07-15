<template>
    <div class="min-h-screen bg-gray-100 py-6 flex flex-col justify-center sm:py-12">
        <div class="relative py-3 sm:max-w-xl sm:mx-auto">
            <div
                class="absolute inset-0 bg-gradient-to-r from-blue-300 to-blue-600 shadow-lg transform -skew-y-6 sm:skew-y-0 sm:-rotate-6 sm:rounded-3xl">
            </div>
            <div class="relative px-4 py-10 bg-white shadow-lg sm:rounded-3xl sm:p-20">
                <div class="max-w-md mx-auto">
                    <div>
                        <h1 class="text-2xl font-semibold">Login Form with Floating Labels</h1>
                    </div>
                    <div class="divide-y divide-gray-200">
                        <div class="py-8 text-base leading-6 space-y-4 text-gray-700 sm:text-lg sm:leading-7">
                            <div class="relative">
                                <input autocomplete="off" v-model="authen.username" id="username" name="username"
                                    type="text"
                                    class="peer placeholder-transparent h-10 w-full border-b-2 border-gray-300 text-gray-900 focus:outline-none focus:borer-rose-600"
                                    placeholder="Username" />
                                <label for="email"
                                    class="absolute left-0 -top-3.5 text-gray-600 text-sm peer-placeholder-shown:text-base peer-placeholder-shown:text-gray-440 peer-placeholder-shown:top-2 transition-all peer-focus:-top-3.5 peer-focus:text-gray-600 peer-focus:text-sm">Username</label>
                            </div>
                            <div class="relative">
                                <input autocomplete="off" v-model="authen.password" id="password" name="password"
                                    type="password"
                                    class="peer placeholder-transparent h-10 w-full border-b-2 border-gray-300 text-gray-900 focus:outline-none focus:borer-rose-600"
                                    placeholder="Password" />
                                <label for="password"
                                    class="absolute left-0 -top-3.5 text-gray-600 text-sm peer-placeholder-shown:text-base peer-placeholder-shown:text-gray-440 peer-placeholder-shown:top-2 transition-all peer-focus:-top-3.5 peer-focus:text-gray-600 peer-focus:text-sm">Password</label>
                            </div>
                            <div class="relative">
                                <button @click="loginn(); $event.preventDefault()"
                                    class="bg-blue-500 text-white rounded-md px-2 py-1">Submit</button>
                            </div>
                            <Notification :status="status" :object="'tài khoản'" :action="'Đăng nhập'"
                                :isShowNoti="showNoti" v-if="showNoti == 'Ok'">
                            </Notification>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
// import { mapActions } from 'vuex';
export default {
    data() {
        return {
            authen: {
                username: null,
                password: null,
            },
            showNoti: '',
            status: '',
        }
    },
    methods: {
        // ...mapActions('auth', ['login']),
        async loginn() {
            try {
                const username = this.authen.username;
                const password = this.authen.password;
                console.log(this.authen);
                await this.$store.dispatch('auth/login', { username, password });
                this.status = "thành công";
                this.showNoti = "Ok";
                setTimeout(() => {
                    this.showNoti = "";
                }, 1500);
                const userRole = this.$store.state.auth.role;
                console.log(userRole);
                // Redirect based on the user's role
                if (userRole === 'STAFF') {
                    this.$router.push({ path: '/admin' })
                } else {
                    this.$router.push({ path: '/' });
                }
            } catch (error) {
                this.status = "thất bại";
                this.showNoti = "Ok";
                setTimeout(() => {
                    this.showNoti = "";
                }, 1500);
                console.error(error);
            }
        }
    }
}
</script>