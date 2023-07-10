<template>
    <div class="cnt4">
        <h3 class="mb-10 mt-5 txt-rgt text-lg font-medium leading-none text-gray-900 dark:text-white">Payment</h3>
        <Credit/>
        <PopupConfirm :title="`mua vé`" @action="submit" v-show="isShowPopup" @closePopup="closePopup">
        </PopupConfirm>
        <button type="submit"
        class="text-white bg-gradient-to-r from-cyan-500 to-blue-500 hover:bg-gradient-to-bl focus:ring-4 focus:outline-none focus:ring-cyan-300 dark:focus:ring-cyan-800 font-medium rounded-lg text-sm px-5 py-2.5 text-center mb-2 left2"
        @click.prevent="isShowPopup = true">
        Buy ticket
    </button>
</div>
</template>
<script>
import Credit from '~/components/Credit.vue';
import PopupConfirm from '~/components/PopupConfirm.vue'
export default {
    data() {
        return {
            isShowPopup: false,
        };
    },
    methods: {
        submit() {
            this.$emit("step",4);
        }
    },
    components: { Credit, PopupConfirm }
}
</script>
<style>
.cnt4{
    margin-right: 300px;
}
.txt-rgt {
    margin-left: 730px;
}
</style>