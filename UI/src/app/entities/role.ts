export class Role{
    constructor(
        public name: string,
        public users: bigint[]
    ){}
}