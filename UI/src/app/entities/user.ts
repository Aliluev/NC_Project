export class User{
    constructor(
        public username:bigint, 
        public phone:string,
        public email:string,
        public roles:bigint[] 
        ){}
}