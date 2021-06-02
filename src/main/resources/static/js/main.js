const apiAuth = Vue.resource('/auth{/id}')

const apiRegistration = Vue.resource('/auth/registration')

const apiIngredients = Vue.resource('/ingredients{/id}')

Vue.component('registration-form', {
    data: function () {
        return {
            login: '',
            password: '',
        }
    },
    template:
        '<div>' +
        '<input v-model="login" type="text" placeholder="Login" />' +
        '<input v-model="password" type="password" placeholder="Password" />' +
        '<input @click="register" type="button" value="Sign up" />' +
        '</div>',
    methods: {
        register: function () {
            const auth = {
                login: this.login,
                password: this.password
            }
            apiRegistration.save({}, auth).then(response =>
                response.json().then(data => {
                    this.$emit('register', data)
                    this.login = ''
                    this.password = ''
                })
            )
        }
    }
})

Vue.component('users-list', {
    props: ['users'],
    template:
        '<div>' +
        '<div v-for="(item, index) in users" :key="index">{{item.id}} {{item.login}}</div>' +
        '</div>'
})

Vue.component('ingredients-list', {
    props: ['ingredients'],
    template:
        '<div>' +
            '<ingredient-row @change="changeIngredients" v-for="(item, index) in ingredients" :key="index" :ingredient="item"/>' +
        '</div>',
    methods:{
        changeIngredients: function (){
            this.$emit('change')
        }
    }
})

Vue.component('ingredient-row', {
    props: ['ingredient'],
    data: function (){
        return{
            editState: false,
        }
    },
    template:
        '<div>' +
            '<div>' +
                '<span>{{ingredient.name}} {{ingredient.price}}</span>' +
                '<button @click="openEditForm">Edit</button>' +
                '<button @click="deleteIngredient">Delete</button>' +
            '</div>' +
            '<ingredient-form v-if="editState" @edit="editIngredient" :id="ingredient.id"/>' +
        '</div>',
    methods:{
        openEditForm: function (){
            this.editState = true
        },
        editIngredient: function (){
            this.$emit('change')
            this.editState = false
        },
        deleteIngredient: function (){
            apiIngredients.remove({id: this.ingredient.id}).then(response => {
                if(response.ok){
                    this.$emit('change', this.ingredient.id)
                }
            })
        }
    }
})

Vue.component('ingredient-form', {
    props: {
        id: {
            type: String,
            default: function(){
                return ''
            }
        }
    },
    data: function () {
        return {
            name: '',
            price: '',
        }
    },
    created: function(){
        if(this.id !== ''){
            apiIngredients.get({id: this.id}).then(response =>
                response.json().then(data => {
                    this.name = data.name
                    this.price = data.price
                })
            )
        }
    },
    template:
        '<div>' +
            '<input v-model="name" type="text" placeholder="Ingredient name" />' +
            '<input v-model="price" type="text" placeholder="Ingredient price" />' +
            '<input v-if="isCreate" @click="add" type="button" value="Add ingredient" />' +
            '<input v-else @click="edit" type="button" value="Edit ingredient" />' +
        '</div>',
    computed: {
        isCreate(){
            return this.id === ''
        }
    },
    methods: {
        add: function () {
            const ingredient = {
                name: this.name,
                price: this.price
            }
            apiIngredients.save({}, ingredient).then(response =>
                response.json().then(data => {
                    this.$emit('add', data)
                    this.name = ''
                    this.price = ''
                })
            )
        },
        edit: function (){
            console.log(this.id)
            const ingredient = {
                name: this.name,
                price: this.price
            }
            apiIngredients.update({id: this.id}, ingredient).then(response =>
                response.json().then(data => {
                    this.$emit('edit', data)
                })
            )
        }
    }
})

var app = new Vue({
    el: '#app',
    template:
        '<div>' +
            '<users-list :users="users"/>' +
            '<ingredients-list @change="changeIngredients()" :ingredients="ingredients"/>' +
            '<ingredient-form @add="addIngredient($event)"/>' +
        '</div>',
    data: function () {
        return {
            users: [],
            ingredients: [],
        }
    },
    created: function () {
        apiAuth.get().then(response =>
            response.json().then(data =>
                data.forEach(item => this.users.push(item))
            )
        )
        this.loadIngredients()
    },
    methods: {
        loadIngredients(){
            this.ingredients = []
            apiIngredients.get().then(response =>
                response.json().then(data =>
                    data.forEach(item => this.ingredients.push(item))
                )
            )
        },
        addUser: function (user) {
            this.users.push(user)
        },
        addIngredient: function (ingredient) {
            this.ingredients.push(ingredient)
        },
        changeIngredients: function () {
            this.loadIngredients()
        }
    }
})