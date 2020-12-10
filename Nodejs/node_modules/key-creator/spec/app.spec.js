const key = require('../dist/app')

describe('Generate Functionality Test', () => {
    it('should return a key with length 16', () => {
        expect(key.generate().length).toEqual(16)
    })
    it('should return a key with length 24', () => {
        expect(key.generate(24).length).toEqual(24)
    })
})